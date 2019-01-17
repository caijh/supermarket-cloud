package com.coding.supermarket.serviceadmin.config.datasource;

import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

@Configuration
public class JtaConfig {

    @Bean(name = "userTransaction")
    public UserTransaction userTransaction() throws SystemException {
        UserTransactionImp userTransactionImp = new UserTransactionImp();
        userTransactionImp.setTransactionTimeout(10000);
        return userTransactionImp;
    }

    @Bean(name = "atomikosTransactionManager")
    public UserTransactionManager atomikosTransactionManager() {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(false);
        return userTransactionManager;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(UserTransaction userTransaction,
                                                         @Qualifier("atomikosTransactionManager") UserTransactionManager atomikosTransactionManager) {
        AtomikosJtaPlatform.userTransaction = userTransaction;
        AtomikosJtaPlatform.userTransactionManager = atomikosTransactionManager;
        return new JtaTransactionManager(userTransaction, atomikosTransactionManager);
    }

    public static class AtomikosJtaPlatform extends AbstractJtaPlatform {

        private static UserTransaction userTransaction;

        private static UserTransactionManager userTransactionManager;

        @Override
        protected TransactionManager locateTransactionManager() {
            return userTransactionManager;
        }

        @Override
        protected UserTransaction locateUserTransaction() {
            return userTransaction;
        }

    }

}
