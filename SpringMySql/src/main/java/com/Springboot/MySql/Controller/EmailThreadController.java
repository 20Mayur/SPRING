package com.Springboot.MySql.Controller;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Springboot.MySql.Entities.Notification;
import com.Springboot.MySql.Entities.Status;
import com.Springboot.MySql.Request.EmailRequest;
import com.Springboot.MySql.Service.EmailThreadService;
@RestController
@RequestMapping("/threadMail")
public class EmailThreadController {

		    @Autowired
		    private EmailThreadService emailService;
		    @Autowired
		    private MongoTemplate mongoTemplate;
		 

		    @PostMapping("/send-email")
		    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest) {
		        try {
		            emailService.sendEmailToMultipleAddresses(emailRequest.getId(), emailRequest.getEmailAddresses(), emailRequest.getSubject(), emailRequest.getMessage());
		            return new ResponseEntity<>("Email sent successfully", HttpStatus.OK);
		        } catch (ExecutionException | InterruptedException e) {
		            e.printStackTrace();
		            return new ResponseEntity<>("Failed to send email", HttpStatus.INTERNAL_SERVER_ERROR);
		        }
		    }
		   @GetMapping("failed/status/{status}")
		   public List<String> retry(@RequestBody EmailRequest emailRequest,@PathVariable Status status){
			Aggregation agg=Aggregation.newAggregation(
                        Aggregation.project("id","to","from","Status"),
                        Aggregation.unwind("Status"),
                        Aggregation.match(Criteria.where("status.status").is(status)),
                        Aggregation.project("to")
					);
			AggregationResults<Document> results =mongoTemplate.aggregate(agg, "Notification",Document.class);
            List<Document> documents = results.getMappedResults();

            List<String> data = new ArrayList<>();
            for (Document doc : documents) {
                String to = doc.getString("to");
                List<String> retry = new ArrayList<>();
                    retry.add(to);
                data.addAll(retry);
        
		}
			return data;
			
		}
}   	
		    

	


