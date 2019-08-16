def solution():
    pass


def int_one():
    return int(input_fn().strip())


def int_list():
    return list(map(int, input_fn().strip().split(" ")))


def str_list():
    return input_fn().strip().split(" ")


input_fn = None

if __name__ == '__main__':
    f = open("D:\\Dev\\Projects\\learning\\ccdsap\\input.txt")
    input_fn = input
    input_fn = f.readline
    solution()
    f.close()
