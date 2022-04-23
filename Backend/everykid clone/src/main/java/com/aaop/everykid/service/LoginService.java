<<<<<<< HEAD:Backend/everykid/src/main/java/com/aaop/everykid/service/LoginService.java
package com.aaop.everykid.service;

import com.aaop.everykid.entity.Parent;
import com.aaop.everykid.repository.ParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService  {

    @Autowired
    private final ParentRepository parentRepository;

    public boolean login(Parent parent){
        Parent findParent = parentRepository.findParentBypID(parent.getPID());
        if(findParent == null){
            return false;
        }
        else return true;
    }
}
=======
package com.aaop.everykid.service;

import com.aaop.everykid.entity.Parent;
import com.aaop.everykid.repository.ParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {

    private final ParentRepository parentRepository;

    public boolean login(Parent parent){
        Parent findParent = parentRepository.findBypID(parent.getPID());
        if(findParent == null){
            return false;
        }
        return true;
    }
}
>>>>>>> b1005988be15d44d4982750d142aa5d96d91e646:Backend/everykid clone/src/main/java/com/aaop/everykid/service/LoginService.java
