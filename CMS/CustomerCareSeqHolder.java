package CMS;

/**
 * Generated from IDL alias "CustomerCareSeq".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at Feb 27, 2025, 12:43:41 AM
 */

public final class CustomerCareSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public CMS.CustomerCare[] value;

	public CustomerCareSeqHolder ()
	{
	}
	public CustomerCareSeqHolder (final CMS.CustomerCare[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return CustomerCareSeqHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = CustomerCareSeqHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		CustomerCareSeqHelper.write (out,value);
	}
}
