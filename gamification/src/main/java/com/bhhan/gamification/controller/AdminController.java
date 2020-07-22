package com.bhhan.gamification.controller;

import com.bhhan.gamification.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hbh5274@gmail.com on 2020-07-22
 * Github : http://github.com/bhhan5274
 */

@Profile("test")
@RestController
@RequestMapping("/gamification/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/delete-db")
    public ResponseEntity deleteDatabase(){
        adminService.deleteDatabaseContents();
        return ResponseEntity.ok().build();
    }
}
