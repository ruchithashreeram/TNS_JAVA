package corejava;


class Payment
{
	void makePayment()
	{
		System.out.println("Processing payment using generic method");
	}
}


class UpiPayment extends  Payment
{
	void makePayment()
	{
		System.out.println("Payment made using UPI");
	}
}

class CardPayment extends Payment
{
	void makePayment()
	{
		System.out.println("Payment made using Credit/debit card");
	}
}


class Walletpayment extends Payment
{
	void makePayment()
	{
		System.out.println("Payment made using Digital Wallet");
	}
}

public class MethodOverridingDemo {

	public static void main(String[] args) {
		Payment payment;//reference of parent class
		
		payment=new UpiPayment();
		payment.makePayment();
		
		payment =new CardPayment();
		payment.makePayment();
		
		payment=new Walletpayment();
		payment.makePayment();
	}

}