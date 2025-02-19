package com.team701.buddymatcher.controllers.pairing;

import com.team701.buddymatcher.dtos.pairing.AddBuddyDTO;
import com.team701.buddymatcher.dtos.pairing.RemoveBuddyDTO;
import com.team701.buddymatcher.services.pairing.PairingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Random;

@ExtendWith(MockitoExtension.class)
public class PairingControllerIntegrationTest {

    @Mock
    private PairingService pairingService;

    @InjectMocks
    private PairingController pairingController;

    @Test
    void addValidNewBuddy() {
        Long userId = new Random().nextLong();
        Long buddyId = new Random().nextLong();

        AddBuddyDTO buddyRequest = createMockedAddBuddyDTO(userId, buddyId);

        ResponseEntity response = pairingController.addBuddy(buddyRequest);

        //Temp response
        String success = String.format("\"Success: %s, %s \"", userId, buddyId);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody(), success);
    }

    AddBuddyDTO createMockedAddBuddyDTO(Long userId, Long buddyId) {
        var dto = new AddBuddyDTO();
        dto.setUserId(userId);
        dto.setBuddyId(buddyId);
        return dto;
    }

    @Test 
    void removeValidBuddy() {
        Long userId = new Random().nextLong();
        Long buddyId = new Random().nextLong();

        RemoveBuddyDTO buddyRequest = createMockedRemoveBuddyDTO(userId, buddyId);

        ResponseEntity response = pairingController.removeBuddy(buddyRequest);

        //Temp response
        String success = String.format("\"Removed: %s, %s \"", userId, buddyId);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody(), success);
    }

    RemoveBuddyDTO createMockedRemoveBuddyDTO(Long userId, Long buddyId) {
        var dto = new RemoveBuddyDTO();
        dto.setUserId(userId);
        dto.setBuddyId(buddyId);
        return dto;
    }
}
