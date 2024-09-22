package com.ruhuna.reservationsystembackend.controller;

import com.ruhuna.reservationsystembackend.dto.ReservationDto;
import com.ruhuna.reservationsystembackend.dto.UnavailableDatesDto;
import com.ruhuna.reservationsystembackend.dto.common.CommonResponse;
import com.ruhuna.reservationsystembackend.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/reservation")
@RequiredArgsConstructor
public class ReservationServiceController {

    private final ReservationService reservationService;

    @GetMapping("/unavailable-dates")
    public List<UnavailableDatesDto> getUnavailableDates() {
        return reservationService.getUnavailableDates();
    }
    @PostMapping(value = "/submit-form",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> submitForm(@Valid @RequestBody ReservationDto reservationDto){
        try{
            reservationService.submitForm(reservationDto);
            return ResponseEntity.ok(new CommonResponse<>(true,"Form submitted successfully"));
        }catch (RuntimeException exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }
}
