dynamic guard(Map<String, dynamic>? obj, String path, dynamic dval) {
  if (path.isEmpty && obj != null) {
    return obj;
  }

  List<String> keys = path.split('.');
  dynamic current = obj;

  for (String key in keys) {
    if (current is Map<String, dynamic> && current.containsKey(key)) {
      current = current[key];
    } else {
      return dval;
    }
  }

  return current ?? dval;
}

  if (!path.startsWith('.') && !path.endsWith('.')) {
    List<String> p = path.split('.');
    if (p.isNotEmpty) {
      return p.fold<dynamic>(obj, (o, n) => (o != null && o[n] != null) ? o[n] : dval);
    }
  }
  return dval;
}

  if (obj == null) {
    return dval;
  }
  return obj;
}

