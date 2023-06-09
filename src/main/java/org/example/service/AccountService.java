package org.example.service;

import org.example.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class AccountService {

    @Autowired
    private PersonRepository personRepository;

    public void addBalance(String username, String balanceStr) {
        int balance = Integer.parseInt(balanceStr);
        personRepository.updateBalanceByUsername(username, balance);
    }

    public boolean addOneMouth(String username) {
        int balance = personRepository.findBalanceByUsername(username);
        if (balance < 4000) {
            return false;
        }
        else {
            Date date = personRepository.findSubscriptionByUsername(username);
            if (date == null) {
                ifSubNull(username, -4000, 1);
                return true;
            }
            ifSubNotNull(username, date, -4000, 1);
            return true;
        }
    }

    public boolean addThreeMouth(String username) {
        int balance = personRepository.findBalanceByUsername(username);
        if (balance < 11000) {
            return false;
        }
        else {
            Date date = personRepository.findSubscriptionByUsername(username);
            if (date == null) {
                ifSubNull(username, -11000, 3);
                return true;
            }
            ifSubNotNull(username, date, -11000, 3);
            return true;
        }
    }

    public boolean addSixMouth(String username) {
        int balance = personRepository.findBalanceByUsername(username);
        if (balance < 20000) {
            return false;
        }
        else {
            Date date = personRepository.findSubscriptionByUsername(username);
            if (date == null) {
                ifSubNull(username, -20000, 6);
                return true;
            }
            ifSubNotNull(username, date, -20000, 6);
            return true;
        }
    }

    public boolean addYear(String username) {
        int balance = personRepository.findBalanceByUsername(username);
        if (balance < 40000) {
            return false;
        }
        else {
            Date date = personRepository.findSubscriptionByUsername(username);
            if (date == null) {
                ifSubNull(username, -40000, 12);
                return true;
            }
            ifSubNotNull(username, date, -40000, 12);
            return true;
        }
    }

    public boolean selectCoach(String username, String coach) {
        if (personRepository.findSubscriptionByUsername(username) == null) {
            return false;
        } else {
            personRepository.updateCoachByUsername(username, coach);
            return true;
        }
    }

    public void ifSubNull(String username, int amount, int mouth) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, mouth);
        Date newDate = calendar.getTime();
        personRepository.updateSubscriptionByUsername(username, newDate);
        personRepository.updateBalanceByUsername(username, amount);
    }

    public void ifSubNotNull(String username, Date date, int amount, int mouth) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, mouth);
        Date newDate = calendar.getTime();
        personRepository.updateSubscriptionByUsername(username, newDate);
        personRepository.updateBalanceByUsername(username, amount);
    }
}
