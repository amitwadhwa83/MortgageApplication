/*
package com.mortagage.unit;

import com.transfer.domain.Account;
import com.transfer.domain.Transfer;
import com.transfer.exception.GenericException;
import com.transfer.exception.InsufficientFundsException;
import com.transfer.exception.InvalidAccountException;
import com.transfer.exception.InvalidAmountException;
import com.transfer.repository.TransferRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.apache.commons.lang.RandomStringUtils.randomAlphanumeric;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class TransferServiceImplTest {

    @InjectMocks
    TransferService service = new TransferServiceImpl();

    @Mock
    TransferRepository transferRepository;
    @Mock
    AccountService accountService;

    @Captor
    ArgumentCaptor<Transfer> transferArgCaptor;

    Random random = new Random();

    @Test
    public final void testFindTransfers() {
	// GIVEN
	Iterable<Transfer> listTransfer = aTransferList();
	when(transferRepository.findAll()).thenReturn(listTransfer);

	// WHEN
	Iterable<Transfer> response = service.findTransfers();

	// THEN
	// Expected invocations
	verify(transferRepository, times(1)).findAll();
	// Assertions
	assertThat(listTransfer, is(equalTo(response)));
    }

    @Test(expected = InvalidAccountException.class)
    public final void testDoTransferFailsForValidationEmptyAccounts() throws GenericException {
	// GIVEN
	String sourceAccountName = "";
	String destAccountName = randomAlphanumeric(10);
	BigDecimal amount = new BigDecimal(random.nextInt(1000000));

	// WHEN
	service.doTransfer(sourceAccountName, destAccountName, amount);

	// THEN
	// Expected exception
    }

    @Test(expected = RuntimeException.class)
    public final void testDoTransferFailsForValidationAccountNotExist() throws GenericException {
	// GIVEN
	String sourceAccountName = randomAlphanumeric(10);
	String destAccountName = randomAlphanumeric(10);
	BigDecimal amount = new BigDecimal(random.nextInt(1000000));
	when(accountService.accountExists(anyString())).thenReturn(false);

	// WHEN
	service.doTransfer(sourceAccountName, destAccountName, amount);

	// THEN
	// Expected exception
    }

    @Test(expected = InvalidAmountException.class)
    public final void testDoTransferFailsForValidationNullAmount() throws GenericException {
	// GIVEN
	String sourceAccountName = randomAlphanumeric(10);
	String destAccountName = randomAlphanumeric(10);
	BigDecimal amount = null;
	when(accountService.accountExists(anyString())).thenReturn(true);

	// WHEN
	service.doTransfer(sourceAccountName, destAccountName, amount);

	// THEN
	// Expected exception
    }

    @Test(expected = InvalidAmountException.class)
    public final void testDoTransferFailsForValidationNegativeAmount() throws GenericException {
	// GIVEN
	String sourceAccountName = randomAlphanumeric(10);
	String destAccountName = randomAlphanumeric(10);
	BigDecimal amount = new BigDecimal(random.nextInt(1000000)).negate();
	when(accountService.accountExists(anyString())).thenReturn(true);

	// WHEN
	service.doTransfer(sourceAccountName, destAccountName, amount);

	// THEN
	// Expected exception
    }

    @Test(expected = InsufficientFundsException.class)
    public final void testDoTransferFailsForValidationInsufficientAccountBalance() throws GenericException {
	// GIVEN
	Account account = new Account();
	account.setBalance(new BigDecimal(0));
	account.setName(randomAlphanumeric(10));
	String destAccountName = randomAlphanumeric(10);
	BigDecimal amount = new BigDecimal(random.nextInt(1000000));
	when(accountService.accountExists(anyString())).thenReturn(true);
	when(accountService.findAccount(account.getName())).thenReturn(account);

	// WHEN
	service.doTransfer(account.getName(), destAccountName, amount);

	// THEN
	// Expected exception
    }

    @Test
    public final void testDoTransferCreatesATransfer() throws GenericException {
	// GIVEN
	Account sourceAccount = new Account();
	sourceAccount.setBalance(new BigDecimal(random.nextInt(1000000)));
	sourceAccount.setName(randomAlphanumeric(10));

	Account destAccount = new Account();
	destAccount.setBalance(new BigDecimal(random.nextInt(1000000)));
	destAccount.setName(randomAlphanumeric(10));

	BigDecimal amount = new BigDecimal(random.nextInt(100));
	Transfer transfer = aTransfer();
	when(accountService.accountExists(anyString())).thenReturn(true);
	when(accountService.findAccount(sourceAccount.getName())).thenReturn(sourceAccount);
	when(accountService.findAccount(destAccount.getName())).thenReturn(destAccount);
	when(transferRepository.save(any(Transfer.class))).thenReturn(transfer);

	// WHEN
	service.doTransfer(sourceAccount.getName(), destAccount.getName(), amount);

	// THEN
	// Expected invocations
	verify(accountService, times(2)).findAccount(anyString());
	verify(transferRepository, times(1)).save(transferArgCaptor.capture());

	// Assertions
	assertThat(sourceAccount.getName(), is(equalTo(transferArgCaptor.getAllValues().get(0).getSourceAccount())));
	assertThat(destAccount.getName(), is(equalTo(transferArgCaptor.getAllValues().get(0).getDestAccount())));
	assertThat(amount, is(equalTo(transferArgCaptor.getAllValues().get(0).getAmount())));
    }

    private Transfer aTransfer() {
	Transfer transfer = new Transfer();
	transfer.setId(random.nextLong());
	transfer.setSourceAccount(randomAlphanumeric(10));
	transfer.setDestAccount(randomAlphanumeric(10));
	transfer.setAmount(new BigDecimal(random.nextInt(1000000)));
	return transfer;
    }

    private Iterable<Transfer> aTransferList() {

	int size = random.nextInt(10);
	List<Transfer> listTransfer = new ArrayList<Transfer>(size);
	int count = 0;
	while (count < size) {
	    count++;
	    Transfer transfer = new Transfer();
	    transfer.setId(random.nextLong());
	    transfer.setSourceAccount(randomAlphanumeric(10));
	    transfer.setDestAccount(randomAlphanumeric(10));
	    transfer.setAmount(new BigDecimal(random.nextInt(1000000)));
	    listTransfer.add(transfer);
	}
	return listTransfer;
    }
}
*/
