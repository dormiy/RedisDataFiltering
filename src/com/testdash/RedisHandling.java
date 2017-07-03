package com.testdash;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

import java.lang.String;

/**
 * Created by wuping on 6/22/2017.
 */
public class RedisHandling {

    private String redisServer;
    static RedisClient client;
    static RedisCommands<String, String> commands;
    static StatefulRedisConnection<String, String> connection;

    public RedisHandling(String redisServer) {
        this.redisServer = redisServer;
    }

    public void redisConnect() {
        RedisHandling.client = RedisClient.create (redisServer);
        RedisHandling.connection = client.connect ();
        RedisHandling.commands = connection.sync ();
        System.out.println ("The redis server now is connected. Enjoy");
    }

    public void redisClose() {
        RedisHandling.connection.close ();
        RedisHandling.client.shutdown ();
        System.out.println ("The redis server is disconnected, client is shut down. Bye!");
    }
}
