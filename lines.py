import os

lines = 0

for directory, folders, files in os.walk('./src/'):
    for file in files:
        file = open(f'{directory}/{file}', 'r', encoding='utf-8')
        lines += len(file.read().split('\n'))

print(lines)
