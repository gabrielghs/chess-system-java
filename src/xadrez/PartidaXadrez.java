package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jogotabuleiro.Peca;
import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;
import xadrez.pecas.Peao;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {

	private int turno;
	private Cor atualJogador;
	private Tabuleiro tabuleiro;
	private boolean check;
	private boolean checkMate;
	
	private List<Peca> pecasTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();
	
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		atualJogador = Cor.BRANCO;
		configuracaoInicial();
	}
	
	public int getTurno() {
		return turno;
	}

	public Cor getAtualJogador() {
		return atualJogador;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
	}

	public PecaXadrez[][] getPecas() {
		PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				mat[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
			}
		}
		return mat;
	}

	public boolean[][] movimentosPossiveis(PosicaoXadrez origem) {
		Posicao posicao = origem.posicionar();
		validarPosicaoOrigem(posicao);
		return tabuleiro.peca(posicao).movimentosPossiveis();
		
	}
	
	public PecaXadrez executarMovimentoDeXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino ) {
		Posicao origem = posicaoOrigem.posicionar();
		Posicao destino = posicaoDestino.posicionar();
		validarPosicaoOrigem(origem);
		validarPosicaoDestino(origem, destino);
		Peca capturarPeca = fazerMover(origem, destino);
		
		if(testeCheck(atualJogador)) {
			desfazerMovimento(origem, destino, capturarPeca);
			throw new XadrezException("Voce nao pode se colocar em check");
		}
		
		check = (testeCheck(oponente(atualJogador))) ? true : false;
		
		if(testeCheckMate(oponente(atualJogador))){
			checkMate = true;
		}
		else {
			proximoTurno();
		}
		
		return (PecaXadrez)capturarPeca;
		
	}
	
	private Peca fazerMover(Posicao origem, Posicao destino) {
		PecaXadrez p = (PecaXadrez)tabuleiro.removerPeca(origem);
		p.aumentarContagemMovimento();
		Peca capturada = tabuleiro.removerPeca(destino);
		tabuleiro.colocarPeca(p, destino);
		
		if(capturada != null) {
			pecasTabuleiro.remove(capturada);
			pecasCapturadas.add(capturada);
		}
		return capturada;
	}
	
	private void desfazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
		PecaXadrez p = (PecaXadrez)tabuleiro.removerPeca(destino);
		p.diminuirContagemMovimento();
		tabuleiro.colocarPeca(p, origem);
		
		if(pecaCapturada != null) {
			tabuleiro.colocarPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasTabuleiro.add(pecaCapturada);
		}
	}
	
	private void validarPosicaoOrigem(Posicao posicao) {
		if(!tabuleiro.existeUmaPeca(posicao)) {
			throw new XadrezException("Não existe peça na posição de origem");
		}
		if(atualJogador != ((PecaXadrez)tabuleiro.peca(posicao)).getCor()) {
			throw new XadrezException("A peca escolhida nao e sua");
		}
		if(!tabuleiro.peca(posicao).existeAlgumMovimentoPossivel()) {
			throw new XadrezException("Nao existe movimentos possiveis para a peca escolhida.");
		}
		 
	}
	
	private void validarPosicaoDestino(Posicao origem, Posicao destino) {
		if(!tabuleiro.peca(origem).movimentosPossiveis(destino)) {
			throw new XadrezException("A peca escolhida nao pode se mover para a posicao de destino");
		}
	}

	private void proximoTurno() {
		turno++;
		atualJogador = (atualJogador == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}

	private Cor oponente(Cor cor) {
		return (cor == Cor.BRANCO) ? Cor.PRETO : cor.BRANCO;
	}
	
	private PecaXadrez rei(Cor cor) {
		List<Peca> lista = pecasTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == cor).collect(Collectors.toList());
		for(Peca p : lista) {
			if(p instanceof Rei) {
				return (PecaXadrez)p;
			}
		}
		throw new IllegalStateException("Não existe rei " + cor + " no tabuleiro");
	}
	
	private boolean testeCheck(Cor cor) {
		Posicao posicaoRei = rei(cor).getPecaXadrez().posicionar();
		List<Peca> pecasOponente = pecasTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == oponente(cor)).collect(Collectors.toList());
		for(Peca p : pecasOponente) {
			boolean[][] mat = p.movimentosPossiveis();
			if(mat[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean testeCheckMate(Cor cor) {
		if(!testeCheck(cor)) {
			return false;
		}
		List<Peca> lista = pecasTabuleiro.stream().filter(x -> ((PecaXadrez)(x)).getCor() == cor).collect(Collectors.toList());
		for(Peca p : lista) {
			boolean[][] mat = p.movimentosPossiveis();
			for(int i=0; i<tabuleiro.getLinhas(); i++) {
				for(int j=0; j<tabuleiro.getColunas(); j++) {
					if(mat[i][j]) {
						Posicao origem = ((PecaXadrez)p).getPecaXadrez().posicionar();
						Posicao destino = new Posicao(i, j);
						Peca pecaCapturada = fazerMover(origem, destino);
						boolean testCheck = testeCheck(cor);
						desfazerMovimento(origem, destino, pecaCapturada);
						if(!testCheck) {
							return false;
						}
					}
					
				}
			}
					
		}
		return true;
		
	}
	
	private void colocarNovaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.colocarPeca(peca, new PosicaoXadrez(coluna, linha).posicionar());
		pecasTabuleiro.add(peca);
	}

	private void configuracaoInicial() {
        colocarNovaPeca('a', 1, new Torre(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('e', 1, new Rei(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('h', 1, new Torre(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('a', 2, new Peao(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('b', 2, new Peao(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('c', 2, new Peao(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('d', 2, new Peao(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('e', 2, new Peao(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('f', 2, new Peao(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('g', 2, new Peao(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('h', 2, new Peao(tabuleiro, Cor.BRANCO));

        colocarNovaPeca('a', 8, new Torre(tabuleiro, Cor.PRETO));
        colocarNovaPeca('e', 8, new Rei(tabuleiro, Cor.PRETO));
        colocarNovaPeca('h', 8, new Torre(tabuleiro, Cor.PRETO));
        colocarNovaPeca('a', 7, new Peao(tabuleiro, Cor.PRETO));
        colocarNovaPeca('b', 7, new Peao(tabuleiro, Cor.PRETO));
        colocarNovaPeca('c', 7, new Peao(tabuleiro, Cor.PRETO));
        colocarNovaPeca('d', 7, new Peao(tabuleiro, Cor.PRETO));
        colocarNovaPeca('e', 7, new Peao(tabuleiro, Cor.PRETO));
        colocarNovaPeca('f', 7, new Peao(tabuleiro, Cor.PRETO));
        colocarNovaPeca('g', 7, new Peao(tabuleiro, Cor.PRETO));
        colocarNovaPeca('h', 7, new Peao(tabuleiro, Cor.PRETO));
	}

}
