package xadrez.pecas;

import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez {

	private PartidaXadrez partidaXadrez;
	
	public Peao(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partidaXadrez) {
		super(tabuleiro, cor);
		this.partidaXadrez = partidaXadrez;
		
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
			//EMPASSAN
			//&& partidaXadrez.getVunerabilidadeEmpassant() != null && p == null && peao.getLinha() == 3
			//Posicao peao = new Posicao(posicao.getLinha(), posicao.getColuna());
			if(posicao.getLinha() == 3) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if(getTabuleiro().posicaoExistente(esquerda) && existePecaOponente(esquerda) && getTabuleiro().peca(esquerda) == partidaXadrez.getVunerabilidadeEmpassant()) {
					mat[esquerda.getLinha() - 1][esquerda.getColuna()] = true;
				}
				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if(getTabuleiro().posicaoExistente(direita) && existePecaOponente(direita) && getTabuleiro().peca(direita) == partidaXadrez.getVunerabilidadeEmpassant()) {
					mat[direita.getLinha() - 1][direita.getColuna()] = true;
				}
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
			p.definirValores(posicao.getLinha() + 1 , p.getColuna() + 2);
			if(getTabuleiro().posicaoExistente(p) && existePecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			//EMPASSAN
			if(posicao.getLinha() == 4) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if(getTabuleiro().posicaoExistente(esquerda) && existePecaOponente(esquerda) && getTabuleiro().peca(esquerda) == partidaXadrez.getVunerabilidadeEmpassant()) {
					mat[esquerda.getLinha() + 1][esquerda.getColuna()] = true;
				}
				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if(getTabuleiro().posicaoExistente(direita) && existePecaOponente(direita) && getTabuleiro().peca(direita) == partidaXadrez.getVunerabilidadeEmpassant()) {
					mat[direita.getLinha() + 1][direita.getColuna()] = true;
				}
			}
		}	
		
		return mat;
	}

	@Override
	public String toString() {
		return "P";
	}
}
