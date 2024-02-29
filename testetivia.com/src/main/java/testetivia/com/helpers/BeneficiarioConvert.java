package testetivia.com.helpers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import testetivia.com.dto.BeneficiarioDto;
import testetivia.com.dto.DocumentoDto;
import testetivia.com.model.BeneficiarioModel;
import testetivia.com.model.DocumentoModel;

import java.util.ArrayList;
import java.util.List;

public class BeneficiarioConvert {
    public BeneficiarioModel BeneficiarioDtoToModel(BeneficiarioDto beneficiario){

        BeneficiarioModel newBeneficiario = new BeneficiarioModel();
        newBeneficiario.setNome(beneficiario.getNome());
        newBeneficiario.setTelefone(beneficiario.getTelefone());
        newBeneficiario.setDataNascimento(beneficiario.getDataNascimento());
        newBeneficiario.setDataInclusao(beneficiario.getDataInclusao());
        newBeneficiario.setDataAtualizacao(beneficiario.getDataAtualizacao());

        List<DocumentoModel> lista = new ArrayList<>();

        for (DocumentoDto docs:beneficiario.getDocumentos()){
            var doc = new DocumentoModel();

            doc.setBeneficiario(newBeneficiario);
            doc.setDescricao(docs.getDescricao());
            doc.setTipoDocumento(docs.getTipoDocumento());
            doc.setDataInclusao(docs.getDataInclusao());
            doc.setDataAtualizacao(docs.getDataAtualizacao());
            lista.add(doc);
        }
        newBeneficiario.setDocumentos(lista);
        return newBeneficiario;
    }

}
