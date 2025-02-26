package CMS;

/**
 * Generated from IDL interface "EmailMarketingService".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at Feb 27, 2025, 12:43:41 AM
 */

public final class EmailMarketingServiceHolder	implements org.omg.CORBA.portable.Streamable{
	 public EmailMarketingService value;
	public EmailMarketingServiceHolder()
	{
	}
	public EmailMarketingServiceHolder (final EmailMarketingService initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return EmailMarketingServiceHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = EmailMarketingServiceHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		EmailMarketingServiceHelper.write (_out,value);
	}
}
