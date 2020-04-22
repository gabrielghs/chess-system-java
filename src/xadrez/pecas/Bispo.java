package xadrez.pecas;

import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Bispo extends PecaXadrez {

	public Bispo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		// Movimento de X
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		Posicao p = new Posicao(0, 0);
		// CANTO DIRETO SUPERIOR
		p.definirValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExistente(p) && !getTabuleiro().existeUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.definirValores(p.getLinha() - 1, p.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExistente(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// CANTO ESQUERDO SUPERIOR
		p.definirValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExistente(p) && !getTabuleiro().existeUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.definirValores(p.getLinha() - 1, p.getColuna() - 1);
		}
		if (getTabuleiro().posicaoExistente(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// CANTO INFERIOR DIREITO
		p.definirValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExistente(p) && !getTabuleiro().existeUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.definirValores(p.getLinha() + 1, p.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExistente(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// CANTO INFERIOR DIREITO
		p.definirValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExistente(p) && !getTabuleiro().existeUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.definirValores(p.getLinha() + 1, p.getColuna() - 1);
		}
		if (getTabuleiro().posicaoExistente(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		return mat;
	}

	@Override
	public String toString() {
		return "B";
	}
}
