package com.okoho.okoho.rest;

import com.okoho.okoho.domain.Blog;
import com.okoho.okoho.domain.Candidat;
import com.okoho.okoho.domain.CategoryJob;
import com.okoho.okoho.domain.CommentBlog;
import com.okoho.okoho.rest.errors.BadRequestAlertException;
import com.okoho.okoho.service.BlogService;
import com.okoho.okoho.service.dto.BlogDTO;
import com.okoho.okoho.service.dto.CommentBlogDTO;
import com.okoho.okoho.utils.HeaderUtil;
import com.okoho.okoho.utils.PaginationUtil;
import com.okoho.okoho.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/v1")
@CrossOrigin
public class BlogResource {
    private final Logger log = LoggerFactory.getLogger(CategoryJobResource.class);

    private static final String ENTITY_NAME = "blog";

    @Value("${okoho.clientApp.name}")
    private String applicationName;
    @Autowired
    public BlogService blogService;
    @PostMapping("/blogs")
    public ResponseEntity<Blog> createBlog(@RequestBody BlogDTO blogDTO)
            throws URISyntaxException, BadRequestAlertException {
        log.debug("REST request to save CategoryJob : {}", blogDTO);

        Blog result = blogService.save(blogDTO);
        return ResponseEntity
                .created(new URI("/api/blogs/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId()))
                .body(result);
    }
    @PostMapping("/comment-blogs")
    public ResponseEntity<CommentBlog> createBlogComment(@RequestBody CommentBlogDTO commentBlogDTO)
            throws URISyntaxException, BadRequestAlertException {
        log.debug("REST request to save CategoryJob : {}", commentBlogDTO);

        CommentBlog result = blogService.saveComment(commentBlogDTO);
        return ResponseEntity
                .created(new URI("/api/blogs/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId()))
                .body(result);
    }
    @GetMapping("/blogs")
    public ResponseEntity<List<Blog>> getAllBlog(Pageable pageable) {
        Page<Blog> page = blogService.findAllWithEagerRelationships(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page,ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    @GetMapping("/comment-blogs/{id}")
    public ResponseEntity<List<CommentBlog>> getAllComment(Pageable pageable,@PathVariable String id) {
        log.debug("REST request to get a page of DomainActivities");
        Page<CommentBlog> page = blogService.findAllCommentWithEagerRelationships(id,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page,ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    @GetMapping("/blogs/{id}")
    public ResponseEntity<Blog> getBlog(@PathVariable String id) {
        log.debug("REST request to get blog : {}", id);
        Optional<Blog> blog = blogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(blog);
    }
    @GetMapping("/comment-blogs/detail/{id}")
    public ResponseEntity<CommentBlog> getComment(@PathVariable String id) {
        log.debug("REST request to get CommentBlog : {}", id);
        Optional<CommentBlog> commentBlog = blogService.findCommentOne(id);
        return ResponseUtil.wrapOrNotFound(commentBlog);
    }
}
