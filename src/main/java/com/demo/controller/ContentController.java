package com.demo.controller;

import com.demo.dto.Content;
import com.demo.service.ContentProducer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/content")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ContentController {

    @Inject
    private ContentProducer contentProducer;

    @POST
    @Path("/log")
    public Response logContent(Content content) {
        contentProducer.sendContent(content);
        return Response.ok().build();
    }
}