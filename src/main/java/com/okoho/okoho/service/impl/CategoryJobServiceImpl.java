package com.okoho.okoho.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.okoho.okoho.domain.CategoryJob;
import com.okoho.okoho.repository.CategoryJobRepository;
import com.okoho.okoho.service.CategoryJobService;
import com.okoho.okoho.service.dto.CategoryJobDTO;
@Service
public class CategoryJobServiceImpl implements CategoryJobService{

    private final CategoryJobRepository categoryJobRepository;

    public CategoryJobServiceImpl(CategoryJobRepository categoryJobRepository) {
        this.categoryJobRepository = categoryJobRepository;
    }

    @Override
    public CategoryJob save(CategoryJobDTO categoryJob) {
        if(categoryJob.getId()==null){
            
        }
    
        CategoryJob category=new CategoryJob(); 
        if(categoryJob.getId()!=null){
            var cat=categoryJobRepository.findById(categoryJob.getId());
            category=cat.get();  
        }
        category.setDescription(categoryJob.getDescription());
        category.setTitle(categoryJob.getTitle());
        return categoryJobRepository.save(category);
    }

    @Override
    public Optional<CategoryJob> partialUpdate(CategoryJob categoryJob) {
        return categoryJobRepository
        .findById(categoryJob.getId())
        .map(
            existingCategoryJob -> {
                if (categoryJob.getTitle() != null) {
                    existingCategoryJob.setTitle(categoryJob.getTitle());
                }
                if (categoryJob.getDescription() != null) {
                    existingCategoryJob.setDescription(categoryJob.getDescription());
                }

                return existingCategoryJob;
            }
        )
        .map(categoryJobRepository::save);
    }

    @Override
    public Page<CategoryJob> findAll(Pageable pageable) {
        return categoryJobRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    public Optional<CategoryJob> findOne(String id) {
        return categoryJobRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(String id) {
        categoryJobRepository.deleteById(id);
    }
    
}
