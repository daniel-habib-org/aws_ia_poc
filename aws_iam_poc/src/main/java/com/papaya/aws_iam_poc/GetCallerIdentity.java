package com.papaya.aws_iam_poc;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sts.StsClient;
import software.amazon.awssdk.services.sts.model.GetCallerIdentityResponse;
import software.amazon.awssdk.services.sts.model.StsException;

import java.util.Map;

@Service
public class GetCallerIdentity {

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println();
        System.out.println("Environment variables:");
        System.out.println();
        Map<String, String> env = System.getenv();
        for (String key : env.keySet()) {
            System.out.println(key + ":" + env.get(key));
        }
        System.out.println();
        System.out.println();

        Region region = Region.EU_WEST_1;
        StsClient stsClient = StsClient.builder()
                .region(region)
                .build();

        getCallerId(stsClient);
        stsClient.close();
    }

    public static void getCallerId(StsClient stsClient) {
        try {
            GetCallerIdentityResponse response = stsClient.getCallerIdentity();

            System.out.println("The user id is " + response.userId());
            System.out.println("The ARN value is " + response.arn());
            System.out.println();

        } catch (StsException e) {
            System.err.println(e.getMessage());
        }
    }

}
