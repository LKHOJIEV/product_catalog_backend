package com.ucell.backend.service;

import com.ucell.backend.entity.Category;
import com.ucell.backend.repository.CategoryRepository;
import com.ucell.backend.response.ApiResponseV1;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    private UsersService usersService;

    public CategoryService(UsersService usersService) {
        this.usersService = usersService;
    }

    public ApiResponseV1 getCategoryList(String userName, String password, Integer detail, Integer offset, Integer limit) throws Exception {

        if (!usersService.isRightUserWithPass(userName,password).getIsAccepted()){
            return new ApiResponseV1(
                    usersService.isRightUserWithPass(userName,password).getStatus(),
                    usersService.isRightUserWithPass(userName,password).getMessage(),
                    null,
                    0L,
                    offset,
                    limit);
        }

        Pageable pageable = PageRequest.of(offset,limit);

        Page<Category> categoryList =
                detail==1
                        ? categoryRepository.findAll(pageable)
                        : categoryRepository.findAllOnlyNode(pageable);

        return new ApiResponseV1(
                usersService.isRightUserWithPass(userName,password).getStatus(),
                categoryList.getTotalElements()>0 ? "success" : "data not found",
                categoryList.toList(),
                categoryList.getTotalElements(),
                offset,
                limit);

    }

    public ApiResponseV1 getCategoryById(String user, String password, String id, Integer detail, Integer offset, Integer limit) throws Exception {

        if (!usersService.isRightUserWithPass(user,password).getIsAccepted()){
            return new ApiResponseV1(
                    usersService.isRightUserWithPass(user,password).getStatus(),
                    usersService.isRightUserWithPass(user,password).getMessage(),
                    null,
                    0L,
                    offset,
                    limit);
        }
            Pageable pageable = PageRequest.of(offset,limit);

            Page<Category> categories =
                    detail==1
                            ? categoryRepository.findByCategoryId(id,pageable)
                            : categoryRepository.findByCategoryIdOnlyNode(id,pageable);

            return new ApiResponseV1(
                    usersService.isRightUserWithPass(user,password).getStatus(),
                    categories.getTotalElements()>0 ? "success" : "data not found",
                    categories.toList(),
                    categories.getTotalElements(),
                    offset,
                    limit);


    }


}
