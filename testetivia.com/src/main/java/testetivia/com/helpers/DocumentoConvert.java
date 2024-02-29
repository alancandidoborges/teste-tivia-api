package testetivia.com.helpers;

import testetivia.com.dto.DocumentoDto;
import testetivia.com.model.DocumentoModel;
public class DocumentoConvert {
    public DocumentoModel DocumentoDtoToModel(DocumentoDto documento){
        var convBenefeciario        = new BeneficiarioConvert();
        DocumentoModel newDocuemnto = new DocumentoModel();

        newDocuemnto.setId(documento.getId());
        newDocuemnto.setTipoDocumento(documento.getTipoDocumento());
        newDocuemnto.setDescricao(documento.getDescricao());
        newDocuemnto.setDataInclusao(documento.getDataInclusao());
        newDocuemnto.setDataAtualizacao(documento.getDataAtualizacao());
        newDocuemnto.setBeneficiario(convBenefeciario.BeneficiarioDtoToModel(documento.getBeneficiario()));
        return  newDocuemnto;
    }
}
