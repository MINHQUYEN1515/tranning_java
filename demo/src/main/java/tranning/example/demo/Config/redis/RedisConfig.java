package tranning.example.demo.Config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
public class RedisConfig {
    @Value("${redis.host}")
    private String hostName;

    // @Bean
    // JedisConnectionFactory jedisConnectionFactory() {
    // JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
    // jedisConFactory.setHostName(hostName);
    // jedisConFactory.setPort(6379);
    // return jedisConFactory;
    // }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(hostName, 6379);
        return new JedisConnectionFactory(config);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setEnableTransactionSupport(true);
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }

    // @Bean
    // @Primary
    // public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory
    // redisConnectionFactory) {
    // // tạo ra một RedisTemplate
    // // Với Key là Object
    // // Value là Object
    // // RedisTemplate giúp chúng ta thao tác với Redis
    // RedisTemplate<Object, Object> template = new RedisTemplate<>();
    // template.setConnectionFactory(redisConnectionFactory);
    // return template;
    // }

}
