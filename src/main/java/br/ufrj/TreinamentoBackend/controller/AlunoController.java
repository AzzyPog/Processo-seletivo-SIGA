package br.ufrj.TreinamentoBackend.controller;

import br.ufrj.TreinamentoBackend.model.entity.Aluno;
import br.ufrj.TreinamentoBackend.repository.AlunoRepository;
import br.ufrj.TreinamentoBackend.service.AlunoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/aluno")
public class AlunoController {
    private final AlunoRepository alunoRepository;
    private final AlunoService alunoService;

    public AlunoController(AlunoRepository alunoRepository, AlunoService alunoService) {
        this.alunoRepository = alunoRepository;
        this.alunoService = alunoService;
    }

    @PostMapping("/")
    public ResponseEntity create(@RequestBody Aluno aluno) {
        var created = alunoService.create(aluno);

        return ResponseEntity.status(201).body(created);
    }

    @GetMapping("/")
    public ResponseEntity findAll(){

        var Alunos = alunoRepository.findAll();

        return ResponseEntity.status(200).body(Alunos);
    }

    @GetMapping("/{matricula}")
    public ResponseEntity findOne(@PathVariable String matricula) {

        var Aluno = alunoService.getAlunoByMatricula(matricula);

        return ResponseEntity.status(200).body(Aluno);
    }

    //fazer na service a verificação do delete
    @DeleteMapping("/{alunoId}")
    public ResponseEntity delete(@PathVariable Long alunoId) {
        var Aluno = alunoService.verifyAlunoById(alunoId);

        alunoRepository.delete(Aluno);

        return ResponseEntity.status(200).body("conta de aluno foi deletada com sucesso.");
    }

    @PutMapping("/{matricula}")
    public ResponseEntity update(@RequestBody Aluno aluno, @PathVariable String matricula) {

        var Aluno = alunoService.updateAluno(matricula, aluno);

        return ResponseEntity.status(200).body(Aluno);

    }

    @PutMapping("/{matricula}/{codigo}")
    public ResponseEntity trocarSituacaoMatricula(@PathVariable String matricula,@PathVariable String codigo) {
        var Aluno = alunoService.updateSituacaoMatricula(codigo, matricula);

        return ResponseEntity.status(200).body(Aluno);
    }

}
