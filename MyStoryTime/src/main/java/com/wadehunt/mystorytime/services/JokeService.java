package com.wadehunt.mystorytime.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.wadehunt.mystorytime.models.Joke;

@Service
public class JokeService {
	
	public Joke getJoke() {
	String url = "https://v2.jokeapi.dev/joke/Any?blacklistFlags=nsfw,explicit";
	WebClient.Builder builder = WebClient.builder();
	Joke joke = builder.build().get().uri(url).retrieve().bodyToMono(Joke.class).block();
	return joke;
	}
}
