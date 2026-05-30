import pygame
from pygame import *

FLIPPED = False
pygame.init()

class Window:
    def __init__(self, width, height, num_layers, scaleFac=1):
        self.screen = display.set_mode((width, height))
        self.layers = [Surface(self.screen.get_size(), pygame.SRCALPHA)]
        self.scale = scaleFac
        for i in range(num_layers-1):
            self.layers.append(Surface(self.screen.get_size(), pygame.SRCALPHA))

    def clear(self, toClear):
        for i in toClear:
            self.layers[i].fill((0, 0, 0, 0))

    def updateScreen(self):
        self.screen.fill((0, 0, 0))
        for layer in self.layers:
            self.screen.blit(layer, (0, 0))
        pygame.display.flip()

    def rect(self, surf, c, x, y, w=1, h=1):
        if not FLIPPED:
            draw.rect(surf, c, (x*self.scale, y*self.scale, w*self.scale, h*self.scale))
        else:
            draw.rect(surf, c, (x*self.scale, self.screen.get_height()-(y+1)*self.scale, w*self.scale, h*self.scale))

    def line(self, surf, c, x1, y1, x2, y2):
        if not FLIPPED:
            draw.aaline(surf, c, (x1*self.scale, y1*self.scale),
                        (x2*self.scale, y2*self.scale))
        else:
            draw.aaline(surf, c, (x1*self.scale, self.screen.get_height()-y1*self.scale),
                        (x2*self.scale, self.screen.get_height()-y2*self.scale))

    def circle(self, surf, c, x, y, r):
        if not FLIPPED:
            draw.circle(surf, c, (x*self.scale, y*self.scale), r*self.scale)
        else:
            draw.circle(surf, c, (x*self.scale, self.screen.get_height()-y*self.scale), r*self.scale)

