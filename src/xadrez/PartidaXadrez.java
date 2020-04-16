package xadrez;

import jogotabuleiro.Peca;
import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {

	private int turno;
	private Cor atualJogador;
	private Tabuleiro tabuleiro;
	
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
		proximoTurno();
		return (PecaXadrez)capturarPeca;
		
	}
	
	private Peca fazerMover(Posicao origem, Posicao destino) {
		Peca p = tabuleiro.removerPeca(origem);
		Peca capturada = tabuleiro.removerPeca(destino);
		tabuleiro.colocarPeca(p, destino);
		return capturada;
		
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
	
	private void colocarNovaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.colocarPeca(peca, new PosicaoXadrez(coluna, linha).posicionar());
	}

	private void configuracaoInicial() {
		colocarNovaPeca('c', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('c', 2, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('d', 2, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('e', 2, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('e', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('d', 1, new Rei(tabuleiro, Cor.BRANCO));

		colocarNovaPeca('c', 7, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('c', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('d', 7, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('e', 7, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('e', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('d', 8, new Rei(tabuleiro, Cor.PRETO));

	}

	
}
