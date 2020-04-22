package xadrez.pecas;

import jogotabuleiro.Peca;
import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez {

	public Peao(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		Posicao p = new Posicao(0, 0);
		
		//Cor branca
		if(getCor() == Cor.BRANCO) {
			p.definirValores(posicao.getLinha() - 1, posicao.getColuna());
			if(getTabuleiro().posicaoExistente(p) && !getTabuleiro().existeUmaPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			//PEGANDO AS 2 LINHAS
			p.definirValores(posicao.getLinha() - 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if(getTabuleiro().posicaoExistente(p) && !getTabuleiro().existeUmaPeca(p) && getTabuleiro().posicaoExistente(p2) && !getTabuleiro().existeUmaPeca(p2) && getContandoMovimento()==0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			//ESQUERDO SUPERIOR
			p.definirValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
			if(getTabuleiro().posicaoExistente(p) && existePecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			//DIREITO SUPERIOR
			p.definirValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
			if(getTabuleiro().posicaoExistente(p) && existePecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
		}
		else {
			//Cor Preta
			p.definirValores(posicao.getLinha() + 1, posicao.getColuna());
			if(getTabuleiro().posicaoExistente(p) && !getTabuleiro().existeUmaPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			//PEGANDO AS 2 LINHAS
			p.definirValores(posicao.getLinha() + 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
			if(getTabuleiro().posicaoExistente(p) && !getTabuleiro().existeUmaPeca(p) && getTabuleiro().posicaoExistente(p2) && !getTabuleiro().existeUmaPeca(p2) && getContandoMovimento()==0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			//ESQUERDO INFERIOR
			p.definirValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
			if(getTabuleiro().posicaoExistente(p) && existePecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			//DIREITO INFERIOR
			p.definirValores(posicao.getLinha() + 1 , p.getColuna() + 1);
			if(getTabuleiro().posicaoExistente(p) && existePecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}	
		}	
		
		return mat;
	}

	@Override
	public String toString() {
		return "P";
	}
}
