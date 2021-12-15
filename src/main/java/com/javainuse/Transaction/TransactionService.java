package com.javainuse.Transaction;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TransactionService {
    void canSave(String EstateId)throws InvalidRollbackInException;

}
