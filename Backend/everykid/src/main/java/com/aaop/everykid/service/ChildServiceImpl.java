/*

package com.aaop.everykid.service;

import com.aaop.everykid.entity.Child;
import com.aaop.everykid.entity.Parent;
import com.aaop.everykid.repository.ChildRepository;
import com.aaop.everykid.repository.ParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChildServiceImpl implements ChildService {

    private final ChildRepository repository;

    @Override
    @Transactional(readOnly = true)
    public void regist(Child child) throws Exception {
        repository.save(child);
    }

    @Override
    @Transactional(readOnly = true)
    public Child read(Long childId) throws Exception {
        return repository.getOne(childId);
    }

    @Override
    @Transactional
    public void modify(Child child) throws Exception {
        Child childEntity = repository.getOne(child.getCKID());

        childEntity.setCNAME(child.getCNAME());
        childEntity.setCAGE(child.getCAGE());
        childEntity.setPictureUrl(child.getPictureUrl());
    }

    @Override
    @Transactional
    public void remove(Long childId) throws Exception {
    repository.deleteById(childId);
    }

    @Override
    @Transactional
    public List<Child> list() throws Exception {
        return repository.findAll(Sort.by(Sort.Direction.DESC,"childId"));
    }

    @Override
    public String getPicture(Long childId) throws Exception {
        Child child = repository.getOne(childId);
        return child.getPictureUrl();
    }

}
*/
