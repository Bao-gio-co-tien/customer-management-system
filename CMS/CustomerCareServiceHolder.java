package CMS;

/**
 * Generated from IDL interface "CustomerCareService".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at Feb 27, 2025, 12:43:41 AM
 */

public final class CustomerCareServiceHolder	implements org.omg.CORBA.portable.Streamable{
	 public CustomerCareService value;
	public CustomerCareServiceHolder()
	{
	}
	public CustomerCareServiceHolder (final CustomerCareService initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return CustomerCareServiceHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = CustomerCareServiceHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		CustomerCareServiceHelper.write (_out,value);
	}
}
