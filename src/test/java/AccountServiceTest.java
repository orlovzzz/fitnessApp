import org.example.repo.PersonRepository;
import org.example.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class AccountServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddBalance() {
        String username = "Artem";
        String balanceStr = "5000";

        accountService.addBalance(username, balanceStr);

        verify(personRepository).updateBalanceByUsername(username, 5000);
    }

    @Test
    void testAddOneMouth() {
        String username = "Artem";
        int balance = 5000;
        Date subscriptionDate = null;

        when(personRepository.findBalanceByUsername(username)).thenReturn(balance);
        when(personRepository.findSubscriptionByUsername(username)).thenReturn(subscriptionDate);

        boolean result = accountService.addOneMouth(username);

        assertTrue(result);
        verify(personRepository).updateBalanceByUsername(username, -4000);
        verify(personRepository).updateSubscriptionByUsername(eq(username), any(Date.class));
    }

    @Test
    void testAddThreeMouth() {
        String username = "Artem";
        int balance = 10000;
        Date subscriptionDate = null;

        when(personRepository.findBalanceByUsername(username)).thenReturn(balance);
        when(personRepository.findSubscriptionByUsername(username)).thenReturn(subscriptionDate);

        boolean result = accountService.addOneMouth(username);

        assertTrue(result);
        verify(personRepository).updateBalanceByUsername(username, -4000);
        verify(personRepository).updateSubscriptionByUsername(eq(username), any(Date.class));
    }

    @Test
    void testAddSixMouth() {
        String username = "Artem";
        int balance = 25000;
        Date subscriptionDate = null;

        when(personRepository.findBalanceByUsername(username)).thenReturn(balance);
        when(personRepository.findSubscriptionByUsername(username)).thenReturn(subscriptionDate);

        boolean result = accountService.addOneMouth(username);

        assertTrue(result);
        verify(personRepository).updateBalanceByUsername(username, -4000);
        verify(personRepository).updateSubscriptionByUsername(eq(username), any(Date.class));
    }

    @Test
    void testAddYear() {
        String username = "Artem";
        int balance = 25000;
        Date subscriptionDate = null;

        when(personRepository.findBalanceByUsername(username)).thenReturn(balance);
        when(personRepository.findSubscriptionByUsername(username)).thenReturn(subscriptionDate);

        boolean result = accountService.addOneMouth(username);

        assertTrue(result);
        verify(personRepository).updateBalanceByUsername(username, -4000);
        verify(personRepository).updateSubscriptionByUsername(eq(username), any(Date.class));
    }
}