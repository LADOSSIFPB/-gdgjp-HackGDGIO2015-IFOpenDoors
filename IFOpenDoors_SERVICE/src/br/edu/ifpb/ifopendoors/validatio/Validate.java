package br.edu.ifpb.ifopendoors.validatio;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.edu.ifpb.ifopendoors.entity.Open;

public class Validate {

	private static Logger logger = LogManager.getLogger(Validate.class);
	
	private static StringValidator stringValidator = new StringValidator();
	private static NumeroValidator numeroValidator = new NumeroValidator();
	private static EmailValidator emailValidator = new EmailValidator();
	private static DataValidator dataValidator = new DataValidator();

	public static int VALIDATE_OK = 0;
	
	public static int open(Open open) {
		
		logger.info("Validação para abertura de sala.");
		//TODO: implementar a validação para abertura de sala.
		return VALIDATE_OK;
	}
}
