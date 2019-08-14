from copy import deepcopy
from math import ceil, log


class SegmentTree:
    def __init__(self, arr):
        size = int(2 * (2 ** ceil(log(len(arr), 2))) - 1)
        self.val = [0] * size
        self.original = deepcopy(arr)
        self.construct(0, 0, len(arr) - 1)

    def query(self, ql, qr):
        return self._query(ql - 1, qr - 1, 0, len(self.original) - 1, 0)

    def _query(self, ql, qr, sl, sr, curr_index):
        if ql <= sl and qr >= sr:
            return self.val[curr_index]
        if qr < sl or ql > sr:
            return 0
        mid = (sl + sr) // 2
        return self._query(ql, qr, sl, mid, SegmentTree.left(curr_index)) + self._query(ql, qr, mid + 1, sr,
                                                                                        SegmentTree.right(curr_index))

    def construct(self, curr_index, left, right):
        if left == right:
            self.val[curr_index] = self.original[left]
            return self.val[curr_index]
        mid = (left + right) // 2
        left_segment = self.construct(SegmentTree.left(curr_index), left, mid)
        right_segment = self.construct(SegmentTree.right(curr_index), mid + 1, right)
        total_segment = left_segment + right_segment
        self.val[curr_index] = max(left_segment, right_segment, total_segment)
        return self.val[curr_index]

    @staticmethod
    def parent(i):
        return (i - 1) / 2

    @staticmethod
    def left(i):
        return 2 * i + 1

    @staticmethod
    def right(i):
        return 2 * i + 2
