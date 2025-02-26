package CMS;

/**
 * Generated from IDL interface "CustomerInfoService".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at Feb 27, 2025, 12:43:41 AM
 */

public final class CustomerInfoServiceHolder	implements org.omg.CORBA.portable.Streamable{
	 public CustomerInfoService value;
	public CustomerInfoServiceHolder()
	{
	}
	public CustomerInfoServiceHolder (final CustomerInfoService initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return CustomerInfoServiceHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = CustomerInfoServiceHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		CustomerInfoServiceHelper.write (_out,value);
	}
}
