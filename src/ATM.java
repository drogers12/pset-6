import java.util.Scanner;

public class ATM {
    
    private Scanner in;
    private BankAccount activeAccount;
    
    public static final int VIEW = 1;
    public static final int DEPOSIT = 2;
    public static final int WITHDRAW = 3;
    public static final int LOGOUT = 4;
    
    public static final int INVALID = 0;
    public static final int INSUFFICIENT = 1;
    public static final int SUCCESS = 2;
    
    public ATM() {
        in = new Scanner(System.in);
        
        activeAccount = new BankAccount(1234, 123456789, new User("Ryan", "Wilson"));
    } //use this to make new acc
    
    public void startup() {
        System.out.println("Welcome to the AIT ATM!\n");
        
        while (true) {
            System.out.print("Account No.: ");
            String accountNo = in.nextLine();					//experimental
            if (accountNo.equals("+")) {
            	System.out.println("ugly");
            } else {
	            boolean numeric = isNumeric (accountNo);
	            System.out.print("PIN        : ");
	            int pin = in.nextInt();
	          
   
	            if (isValidLogin(Long.parseLong(accountNo), pin)) {
	                System.out.println("\nHello, again, " + activeAccount.getAccountHolder().getFirstName() + "!\n");
	                
	                boolean validLogin = true;
	                while (validLogin) {
	                    switch (getSelection()) {
	                        case VIEW: showBalance(); break;
	                        case DEPOSIT: deposit(); break;
	                        case WITHDRAW: withdraw(); break;
	                        //case TRANSFER: transfer(); break;
	                        case LOGOUT: validLogin = false; break;
	                        
	                        default: System.out.println("\nInvalid selection.\n"); break;
	                    }
	                }
	            } else {
	                if (Long.parseLong(accountNo) == -1 && pin == -1) {
	                    shutdown();
	                } else {
	                    System.out.println("\nInvalid account number and/or PIN.\n");
	                }
	            }
        	}
        }
    }
    
    public static boolean isNumeric(String str) { 
	  	 try {  
	  	   Double.parseDouble(str);  
	  	   return true;
	  	 } catch(NumberFormatException e){  
	  	   return false;  
	  	 }  
	 }
    
    public boolean isValidLogin(long accountNo, int pin) {
        return accountNo == activeAccount.getAccountNo() && pin == activeAccount.getPin();
    }
    
    public int getSelection() {
        System.out.println("[1] View balance");
        System.out.println("[2] Deposit money");
        System.out.println("[3] Withdraw money");
        System.out.println("[4] Transfer money");  	//EDITED SWAP BACK TO FIX
        System.out.println("[5] Logout");			//EDITED
        
        return in.nextInt();
    }
    
    public void showBalance() {
        System.out.println("\nCurrent balance: " + activeAccount.getBalance());
    }
    
    public void deposit() {
        System.out.print("\nEnter amount: ");
        double amount = in.nextDouble();
            
        int status = activeAccount.deposit(amount);
        if (status == ATM.INVALID) {
            System.out.println("\nDeposit rejected. Amount must be greater than $0.00.\n");
        } else if (status == ATM.SUCCESS) {
            System.out.println("\nDeposit accepted.\n");
        }
    }
        
    public void withdraw() {
        System.out.print("\nEnter amount: ");
        double amount = in.nextDouble();
            
        int status = activeAccount.withdraw(amount);
        if (status == ATM.INVALID) {
            System.out.println("\nWithdrawal rejected. Amount must be greater than $0.00.\n");
        } else if (status == ATM.INSUFFICIENT) {
            System.out.println("\nWithdrawal rejected. Insufficient funds.\n");
        } else if (status == ATM.SUCCESS) {
            System.out.println("\nWithdrawal accepted.\n");
        }
    }
    
//    public void transfer() {
//    	System.out.println("\nEnter account: ");
//    	double accountNo = in.nextLong();
//    	System.out.print("\nEnter amount: ");
//    	double amount = in.nextDouble();
//    	
//    	
//    	int status = activeAccount.transfer(accountNo, amount);
//    	if (status == ATM.INVALID) {
//    		System.out.println("")
//    	}
//    }     
    
    public void shutdown() {
        if (in != null) {
            in.close();
        }
        
        System.out.println("\nGoodbye!");
        System.exit(0);
    }
    
    public static void main(String[] args) {
        ATM atm = new ATM();
        
        atm.startup();
    }
}
    
    ////////////////////////////////////////////////////////////////////////////
    //                                                                        //
    // Refer to the Simple ATM tutorial to fill in the details of this class. //
    // You'll need to implement the new features yourself.                    //
    //                                                                        //
    ////////////////////////////////////////////////////////////////////////////
    
    /**
     * Constructs a new instance of the ATM class.
     */
    
