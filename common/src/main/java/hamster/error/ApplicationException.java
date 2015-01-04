package hamster.error;

import org.springframework.context.MessageSourceResolvable;

public class ApplicationException extends RuntimeException implements MessageSourceResolvable{

	private static final long serialVersionUID = 1L;

	private final String code;
	
	public ApplicationException(String code) {
		this(null, null, code);
	}

	public ApplicationException(String arg0, Throwable arg1, String code) {
		super(arg0, arg1);
		this.code = code;
	}

	public ApplicationException(String arg0, String code) {
		this(arg0, null, code);
	}

	public ApplicationException(Throwable arg0, String code) {
		this(null, arg0, code);
	}

	@Override
	public Object[] getArguments() {
		return null;
	}

	@Override
	public String[] getCodes() {
		return new String[]{code};
	}

	@Override
	public String getDefaultMessage() {
		return getMessage();
	}

}
