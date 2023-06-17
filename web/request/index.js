// 使用require.context()获取指定目录下所有以.js结尾的文件，但排除了当前目录下的index.js文件
const modulesFilesout = require.context('./api', false, /\.js$/)

// 使用reduce()方法遍历上一步中获取到的所有文件，根据文件名生成API接口对应的模块名，并将文件中导出的API接口合并到一个对象中。
const allModulsout = modulesFilesout.keys().reduce((modules, modulePath) => {
  if (modulePath === './index.js') return modules
  const moduleName = modulePath.replace(/^\.\/(.*)\.\w+$/, '$1')
  const value = modulesFilesout(modulePath)
  modules[moduleName] = value.default
  return modules
}, {})

// 再次使用require.context()获取指定目录下所有以index.js结尾的文件，并遍历每个文件，根据路径名生成模块名，并将文件中导出的API接口合并到同一个对象中。
const modulesFilesinner = require.context('./api', true, /index\.js$/)
const allModulsinner = modulesFilesinner.keys().reduce((modules, modulePath) => {
  if (modulePath === './index.js') return modules
  let moduleName = modulePath.replace(/^\.\/(.*)\.\w+$/, '$1')
  moduleName = moduleName.split('/')[0]
  const value = modulesFilesinner(modulePath)
  modules[moduleName] = value.default
  return modules
}, {})

// 将第1步和第2步中合并得到的对象和第3步中合并得到的对象，用ES6扩展语法将它们合并成一个对象，并通过export default导出。
const allModules = { ...allModulsout, ...allModulsinner }
export default allModules