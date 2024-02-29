package testetivia.com.enums;

import lombok.Getter;

@Getter
public enum Message {
	
	  FILE_NOT_FOUND("Arquivo não encontrado."),    
	  RECORD_ALREADY_EXISTS("Registro já cadastrado."),
	  RECORD_DELETED("Registro deletado com sucesso."),
	  RECORD_NOT_FOUND("Registro não encontrado."),
	  RECORD_NOT_SAVED("Registro não inserido."),
	  RECORD_NOT_UPDATED("Registro não atualizado."),
	  RECORD_SUCCESS("Registro inserido com sucesso."),
	  RECORD_UPDATED("Registro atualizado com sucesso.");  

	  private String message;

	  private Message(String message) {
	      this.message = message;
	  }

	  public String toString() {
	    return message;
	  }
}
