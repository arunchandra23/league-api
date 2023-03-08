package com.woxsen.leagueapi.controller;

import java.util.Map;
import java.util.UUID;

import com.woxsen.leagueapi.payload.request.SlotToArenaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.woxsen.leagueapi.payload.ApiResponse;
import com.woxsen.leagueapi.payload.request.ArenaRequest;
import com.woxsen.leagueapi.service.ArenaService;
import com.woxsen.leagueapi.service.CourseService;
import com.woxsen.leagueapi.utils.AppConstants;

import jakarta.validation.Valid;

@RestController
@RequestMapping(AppConstants.BASE_URL+"/arenas")
public class ArenaController {

    @Autowired
    private ArenaService arenaService;
    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<ApiResponse> addArena(@Valid @RequestBody ArenaRequest arenaRequest){
        ApiResponse apiResponse = arenaService.addArena(arenaRequest);
        return new ResponseEntity<>(apiResponse,apiResponse.getStatus());

    }
    @GetMapping
    public ResponseEntity<ApiResponse> getAllArenas(){
        ApiResponse apiResponse = arenaService.getAllArenas();
        return new ResponseEntity<>(apiResponse,apiResponse.getStatus());

    }

    @PutMapping("/{arenaId}/under-maintenance")
    public ResponseEntity<ApiResponse> markUnderMaintenance(@PathVariable UUID arenaId){
        ApiResponse apiResponse = arenaService.markUnderMaintenance(arenaId);
        return new ResponseEntity<>(apiResponse,apiResponse.getStatus());

    }

    @PostMapping("{arenaId}/slots/{slotId}")
    public ResponseEntity<ApiResponse> addSlotToArena(@RequestBody SlotToArenaRequest slotToArenaRequest, @PathVariable UUID arenaId, @PathVariable UUID slotId){
        ApiResponse apiResponse = arenaService.addSlotToArena(arenaId,slotId,slotToArenaRequest);
        return new ResponseEntity<>(apiResponse,apiResponse.getStatus());

    }

    @GetMapping("{arenaId}/slots")
    public ResponseEntity<ApiResponse> getSlotsByArena(@PathVariable UUID arenaId,String day){
        ApiResponse apiResponse = arenaService.getSlotsByArena(arenaId,day);
        return new ResponseEntity<>(apiResponse,apiResponse.getStatus());

    }
}
