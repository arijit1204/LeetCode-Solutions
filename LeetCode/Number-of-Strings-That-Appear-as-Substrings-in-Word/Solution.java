    int c = 0;
    for (auto &e : patterns) {
        if (word.find(e)!=string::npos) c++;
    }
    return c;