package com.spring.auth.oauth2.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.config.annotation.web.server.EnableSpringWebSession;
import org.springframework.session.data.redis.ReactiveRedisSessionRepository;

@EnableSpringWebSession
@Configuration
public class RedisConfig {

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory();
    }

    //ReactiveRedisSessionRepository

    @Bean
    public ReactiveRedisSessionRepository reactiveRedisSessionRepository(ReactiveRedisTemplate reactiveRedisTemplate) {
        return new ReactiveRedisSessionRepository(reactiveRedisTemplate);
    }

    @Bean
    public RedisTemplate<String, TokenDetails> getRedisTemplate(LettuceConnectionFactory factory) {
        RedisTemplate<String, TokenDetails> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        Jackson2JsonRedisSerializer<TokenDetails> serializer = new Jackson2JsonRedisSerializer<>(TokenDetails.class);
        serializer.setObjectMapper(objectMapper());
        redisTemplate.setValueSerializer(serializer);
        return redisTemplate;
    }

//    @Bean
//    public ReactiveSessionRepository reactiveSessionRepository() {
//        return new ReactiveMapSessionRepository(new ConcurrentHashMap<>());
//    }

    @Bean
    public ReactiveRedisOperations<String, String> redisOperations(
            LettuceConnectionFactory connectionFactory) {

        //Jackson2JsonRedisSerializer<OAuth2AuthorizedClient> valueSerializer =
        //new Jackson2JsonRedisSerializer<>(OAuth2AuthorizedClient.class);

        // valueSerializer.setObjectMapper(objectMapper());

        RedisSerializationContext<String, String> serializationContext =
                RedisSerializationContext
                        .<String, String>newSerializationContext(
                                new StringRedisSerializer())
                        .key(new StringRedisSerializer())
                        .value(new StringRedisSerializer())
                        .build();
        return new ReactiveRedisTemplate<>(connectionFactory, serializationContext);
    }

    private ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //SecurityJackson2Modules.enableDefaultTyping(objectMapper);
        objectMapper.registerModules(new JavaTimeModule());
        return objectMapper;
    }

    @Bean
    public ReactiveStringRedisTemplate reactiveStringRedisTemplate(ReactiveRedisConnectionFactory factory) {
        return new ReactiveStringRedisTemplate(factory);
    }

}
