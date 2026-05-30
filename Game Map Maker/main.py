import pygame
import numpy as np
from graphics import *

width = 1000
height = 1000
gridScale = 16
conversion = 5
screen = Window(width, height, 2, gridScale)

for x in range(int(width/gridScale)):
    screen.line(screen.layers[0], (100, 100, 100), x, 0, x, height)
for y in range(int(height/gridScale)):
    screen.line(screen.layers[0], (100, 100, 100), 0, y, width, y)
screen.rect(screen.layers[0], (150, 150, 150), int(width/gridScale/2)-.1, 0, .2, height)
screen.rect(screen.layers[0], (150, 150, 150), 0, int(height/gridScale/2)-.1, width, .2)

running = True
wallPts = np.array([])
enemyPts = np.array([])

def printPoints():
    midX = width/2/gridScale
    midY = height/2/gridScale
    for i in range(3, len(wallPts), 4):
        x1 = str((wallPts[i-3]-midX)*conversion)
        y1 = str((wallPts[i-2]-midY)*conversion)
        x2 = str((wallPts[i-1]-midX)*conversion)
        y2 = str((wallPts[i]-midY)*conversion)
        print("\twalls.add(new Wall(world, "+x1+", "+y1+", "+x2+", "+y2+"));")
    for i in range(1, len(enemyPts), 2):
        x = str((enemyPts[i-1]-midX)*conversion)
        y = str((enemyPts[i]-midY)*conversion)
        print("\tworld.addObject(new Enemy("+x+", "+y+", 4), 0, 0);")

while running:
    screen.clear([1])
    keys = pygame.key.get_pressed()
    for event in pygame.event.get():
        if event.type == pygame.QUIT or event.type == pygame.KEYDOWN and event.key == pygame.K_RETURN:
            pygame.quit()
            printPoints()
            running = False
        if event.type == pygame.MOUSEBUTTONDOWN:
            if event.button == 1:
                pos = np.array(event.pos) / gridScale
                if keys[pygame.K_LSHIFT]:
                    pos = np.round(pos)
                wallPts = np.append(wallPts, pos)
            if event.button == 3:
                pos = np.array(pygame.mouse.get_pos()) / gridScale
                if keys[pygame.K_LSHIFT]:
                    pos = np.round(pos)
                enemyPts = np.append(enemyPts, pos)
    if running:
        for i in range(3, len(wallPts), 4):
            screen.line(screen.layers[1], (255, 0, 0), wallPts[i-3], wallPts[i-2], wallPts[i-1], wallPts[i])
        for i in range(1, len(enemyPts), 2):
            screen.circle(screen.layers[1], (50, 200, 50), enemyPts[i-1], enemyPts[i], .4)
        if len(wallPts) % 4 == 2:
            mousePos = pygame.mouse.get_pos()
            x = mousePos[0] / gridScale
            y = mousePos[1] / gridScale
            if keys[pygame.K_LSHIFT]:
                x = round(x)
                y = round(y)
            screen.line(screen.layers[1], (255, 0, 0), wallPts[len(wallPts)-2], wallPts[len(wallPts)-1], x, y)
        screen.updateScreen()
