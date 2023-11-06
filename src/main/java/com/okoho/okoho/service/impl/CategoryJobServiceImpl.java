package com.okoho.okoho.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.okoho.okoho.domain.CategoryJob;
import com.okoho.okoho.repository.CategoryJobRepository;
import com.okoho.okoho.service.CategoryJobService;
@Service
public class CategoryJobServiceImpl implements CategoryJobService{

    private final CategoryJobRepository categoryJobRepository;

    public CategoryJobServiceImpl(CategoryJobRepository categoryJobRepository) {
        this.categoryJobRepository = categoryJobRepository;
    }

    @Override
    public CategoryJob save(CategoryJob categoryJob) {
        return categoryJobRepository.save(categoryJob);
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
