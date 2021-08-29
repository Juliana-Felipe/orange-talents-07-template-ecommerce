package br.com.zupacademy.mercadolivre.produto;

import br.com.zupacademy.mercadolivre.categoria.CategoriaRepository;
import br.com.zupacademy.mercadolivre.usuario.Usuario;
import br.com.zupacademy.mercadolivre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<?> cadastraProduto(@RequestBody @Valid ProdutosRequest produtosRequest){
        Optional<Usuario> usuario = usuarioRepository.findByLogin("juliana@gmail");
        Produto produto = produtosRequest.converte(categoriaRepository,usuario.get());
        produtoRepository.save(produto);
        return ResponseEntity.ok(produto.toString());
    }


}
