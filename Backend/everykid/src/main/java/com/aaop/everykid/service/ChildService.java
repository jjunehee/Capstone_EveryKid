
package com.aaop.everykid.service;

import com.aaop.everykid.entity.Child;
import com.aaop.everykid.entity.Parent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface ChildService {
    public void regist(Child child) throws Exception;

    public Child read(Long childId) throws Exception;

    public void modify(Child child) throws Exception;

    public void remove(Long childId) throws Exception;

    public List<Child> list() throws Exception;

    public String getPicture(Long childId) throws Exception;
}
