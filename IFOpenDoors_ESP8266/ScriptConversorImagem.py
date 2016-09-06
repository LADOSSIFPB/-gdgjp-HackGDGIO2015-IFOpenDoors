import cv2
imagem = cv2.imread("imagem.png")
imBin = {}

for i in range(imagem.shape[0]):
	for j in range(imagem.shape[1]):
		a,b,c = imagem[i,j]

		if a!=0 or  b!=0 or c!=0:
			imBin[str(i) + " " + str(j)] = 1

print imBin
