package com.javainuse.Transaction;
import com.javainuse.dao.EstatesRepository;
import com.javainuse.model.DAOEstate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionalService implements TransactionService {
    @Autowired
    EstatesRepository dao;

    @Transactional(rollbackFor = InvalidRollbackInException.class)
    public void canSave(String id)throws InvalidRollbackInException
    {
       DAOEstate sold = dao.findById(Integer.valueOf(id)).get();
        if (sold.isState())
                throw new InvalidRollbackInException("The sale cannot be completed, This Estate was sold ");

    }
}
