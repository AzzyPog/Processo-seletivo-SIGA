package br.ufrj.TreinamentoBackend.service;

import br.ufrj.TreinamentoBackend.model.entity.Aluno;
import br.ufrj.TreinamentoBackend.model.entity.SituacaoMatricula;
import br.ufrj.TreinamentoBackend.repository.AlunoRepository;
import br.ufrj.TreinamentoBackend.repository.SituacaoMatriculaRepository;
import br.ufrj.TreinamentoBackend.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    private final SituacaoMatriculaRepository situacaoMatriculaRepository;

    public AlunoService(AlunoRepository alunoRepository, SituacaoMatriculaRepository situacaoMatriculaRepository) {

        this.alunoRepository = alunoRepository;
        this.situacaoMatriculaRepository = situacaoMatriculaRepository;

    }

    //maneira que o Siga faz
    public Aluno create(Aluno aluno) {
        Optional<Aluno> optionalAluno = alunoRepository.findAlunoByMatricula(aluno.getMatricula());

        if (optionalAluno.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Matrícula já existe.");
        }

        aluno.setSituacaoMatricula(situacaoMatriculaRepository.findSituacaoMatriculaByCodigo(SituacaoMatricula.ATIVO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Situação inexistente.")));

        return alunoRepository.save(aluno);
    }

    public Aluno getAlunoByMatricula(String matricula){
        Optional<Aluno> optAluno = alunoRepository.findAlunoByMatricula(matricula);

        if (optAluno.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado.");
        }

        return optAluno.orElse(null);
    }

    public Aluno verifyAlunoById(Long id) {
        Optional<Aluno> optionalAluno = alunoRepository.findById(id);

        if(optionalAluno.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não existe.");
        }

        return optionalAluno.get();
    }

    @Transactional
    public Aluno updateAluno(String matricula, Aluno aluno) {
        Aluno optionalAluno = alunoRepository.findAlunoByMatricula(matricula)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não existe"));

        Utils.copyNonNullProperties(aluno, optionalAluno);

        return optionalAluno;

    }

    @Transactional
    public Aluno updateSituacaoMatricula(String codigo, String matricula) {
        Aluno aluno = alunoRepository.findAlunoByMatricula(matricula).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        String codigoVariavel;
        switch (codigo) {
            case "ATIVO":
                codigoVariavel = SituacaoMatricula.ATIVO;
                break;
            case "CANCELADO":
                codigoVariavel = SituacaoMatricula.CANCELADO;
                break;
            case "TRANCADO":
                codigoVariavel = SituacaoMatricula.TRANCADO;
                break;
            case "FORMADO":
                codigoVariavel = SituacaoMatricula.FORMADO;
                break;
            default:
                throw new IllegalArgumentException("Código inválido: " + codigo);
        }
        System.out.println(codigoVariavel);

        aluno.setSituacaoMatricula(situacaoMatriculaRepository.findSituacaoMatriculaByCodigo(codigoVariavel)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "A matrícula não pôde ser alterada.")));

        return aluno;
    }
}
