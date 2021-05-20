package com.springbook.common;

import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

// 참고용
// 모든 트랜잭션 관리자는 PlatformTransactionManager 인터페이스를 구현한 클래스이다.
// applicationContext.xml에 등록된 DataSourceTransactionManager도 당연 해당 인터페이스를 구현한 클래스이다.
public interface PlatformTransactionManager {
	TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException;
	// 예외 발생 시 rollback, 정상 수행 종료 시 commit
	void commit(TransactionStatus status) throws TransactionException;
	void rollback(TransactionStatus status) throws TransactionException;
}
