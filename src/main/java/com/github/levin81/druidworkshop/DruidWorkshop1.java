package com.github.levin81.druidworkshop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.levin81.daelic.core.DruidClient;
import com.github.levin81.daelic.core.DruidConfiguration;
import com.github.levin81.daelic.druid.GroupBy;
import com.github.levin81.daelic.druid.Queries;
import com.github.levin81.daelic.druid.result.GroupByResult;

import java.io.IOException;

public class DruidWorkshop1 {

    public static void main(String args[]) {
        ObjectMapper mapper = new ObjectMapper()
                .findAndRegisterModules()
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        DruidConfiguration config = new DruidConfiguration("your.druid.here");
        DruidClient client = new DruidClient(config, mapper);

        // You can use GroupBy.builder() instead
        GroupBy groupBy = Queries.GROUP_BY()
                .withDataSource("some-datasource")
                // add stuff here
                .build();

        try {
            System.out.println("*************************************");
            System.out.println("*****     GROUPBY QUERY JSON    *****");
            System.out.println("*************************************");
            System.out.println(
                    mapper.writerWithDefaultPrettyPrinter().writeValueAsString(groupBy)
            );

            GroupByResult result = client.query(groupBy);

            System.out.println("*************************************");
            System.out.println("***** GROUPBY QUERY RESULT JSON *****");
            System.out.println("*************************************");
            System.out.println(
                    mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
